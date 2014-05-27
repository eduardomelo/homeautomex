using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class DispositivoMap : EntityTypeConfiguration<Dispositivo>
    {
        public DispositivoMap() {

            ToTable("DISPOSITIVO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_DISPOSITIVO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Descricao).HasColumnName("DS_DESCRICAO").IsRequired();
            Property(e => e.DataCadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.DataAlteracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.DataExclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");
            Property(e => e.Status).HasColumnName("STATUS");
            Property(e => e.Favorito).HasColumnName("FAVORITO");
 
            HasRequired(e => e.Ambiente).WithMany().Map(e => e.MapKey("CD_AMBIENTE"));
            HasRequired(e => e.Porta).WithMany().Map(e => e.MapKey("CD_PORTA"));


            HasMany(e => e.Cenario)
              .WithMany(e => e.Dispositivo)

              .Map(e =>
              {
                  e.ToTable("DISPOSITIVO_CENARIO");
                  e.MapLeftKey("CD_CENARIO");
                  e.MapRightKey("CD_DISPOSITIVO");

              });
             
        }

    }

    //public class DispositivoTesteMap : EntityTypeConfiguration<DispositivoTeste>
    //{
    //    public DispositivoTesteMap()
    //    {
    //        ToTable("DISPOSITIVO_TESTE");

    //        HasKey(e => e.Chave);

    //        Property(e => e.Chave)
    //            .HasColumnName("CD_DISPOSITIVO")
    //            .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

    //        Property(e => e.Identificador).HasColumnName("NM_DISPOSITIVO");
    //        Property(e => e.Status).HasColumnName("STATUS");

    //        Ignore(e => e.DataAlteracao);
    //        Ignore(e => e.DataCadastro);
    //        Ignore(e => e.DataExclusao);

    //    }
    //}
}
