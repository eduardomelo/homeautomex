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
    public class LogMap : EntityTypeConfiguration<Log>
    {
        public LogMap()
        {

            ToTable("LOG");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_LOG")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Descricao).HasColumnName("DESCRICAO").IsRequired();
            Property(e => e.Cd_usuario).HasColumnName("CD_USUARIO");
            Property(e => e.DataCadastro).HasColumnName("DATA");

            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
        
        }

    }
}
