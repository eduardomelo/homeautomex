using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class ResidenciaMap : EntityTypeConfiguration<Residencia>
    {
        public ResidenciaMap()
        {
            ToTable("RESIDENCIA");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .IsRequired()
                .HasColumnName("CD_RESIDENCIA")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Nome).HasColumnName("NM_RESIDENCIA").IsRequired();
            Property(e => e.Logradouro).HasColumnName("DS_LOGRADOURO").IsRequired();
            Property(e => e.Cidade).HasColumnName("DS_CIDADE").IsRequired();
            Property(e => e.Bairro).HasColumnName("DS_BAIRRO").IsRequired();
            Property(e => e.Cep).HasColumnName("NM_CEP");
            Property(e => e.Numero).HasColumnName("NM_NUMERO");
            Property(e => e.Complemento).HasColumnName("DS_COMPLEMENTO");
            Property(e => e.DataCadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.DataAlteracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.DataExclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");

            HasMany(e => e.Usuarios)                
                .WithMany(e => e.Residencias)                
                .Map(m =>
                {
                    m.ToTable("USUARIO_RESIDENCIA");
                    m.MapLeftKey("CD_USUARIO");
                    m.MapRightKey("CD_RESIDENCIA");
                });

            //HasMany(e => e.Usuarios)
            //    .WithMany(e => e.Residencias)
            //    .Map(m =>
            //    {
            //        m.ToTable("USUARIO_RESIDENCIA");
            //        m.MapLeftKey("CD_RESIDENCIA");
            //        m.MapRightKey("CD_USUARIO");
            //    });
        }
    }
}
